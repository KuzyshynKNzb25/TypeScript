package com.bit_project.typeweatherwithoutgit.service;

import com.bit_project.typeweatherwithoutgit.dto.CityDto;
import com.bit_project.typeweatherwithoutgit.entity.WithoutResults;
import com.bit_project.typeweatherwithoutgit.json_objects.GEOCODING.GeocodingResponseItem;
import com.bit_project.typeweatherwithoutgit.json_objects.JSON.MainResponse;
import com.bit_project.typeweatherwithoutgit.dto.SimpleWeatherRequest;
import com.bit_project.typeweatherwithoutgit.entity.DailyTemperature;
import com.bit_project.typeweatherwithoutgit.entity.HourlyWeather;
import com.bit_project.typeweatherwithoutgit.entity.WeatherByCity;
import com.bit_project.typeweatherwithoutgit.json_objects.JSON_2_DAYS.ResponseForTwoDays;
import com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS.ListItem;
import com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS.ResponseForFive;
import com.bit_project.typeweatherwithoutgit.json_objects.JSON_8_DAYS.ResponseForEightDays;
import com.bit_project.typeweatherwithoutgit.repository.DailyTemperatureRepository;
import com.bit_project.typeweatherwithoutgit.repository.HourlyWeatherRepository;
import com.bit_project.typeweatherwithoutgit.repository.WeatherByCityRepository;
import com.bit_project.typeweatherwithoutgit.repository.WithoutResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.*;
import java.util.*;


@Service
public class MainService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherByCityRepository weatherByCityRepository;

    @Autowired
    private HourlyWeatherRepository hourlyWeatherRepository;

    @Autowired
    private DailyTemperatureRepository dailyTemperatureRepository;

    @Autowired
    private WithoutResultsRepository withoutResultsRepository;

    @Value("${simple-key}")
    private String simpleKey;

    @Value("${private-key}")
    private String privateKey;

    private final char inAsciCode = 128;
    private final Locale ukrainianLocale = new Locale("uk");

    public MainResponse testRequest(){
        MainResponse response = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=Lviv&units=metric&appid=" + simpleKey,
                MainResponse.class
        );

        return response;
    }


    private List<WeatherByCity> applyGeocodingRequest(String cityName, List<WeatherByCity> cities) {
        GeocodingResponseItem[] response = restTemplate.getForObject(
                "http://api.openweathermap.org/geo/1.0/direct?q="+ cityName+ "&limit=10&appid=" + simpleKey,
                GeocodingResponseItem[].class);

        if(response.length == 0){
            return new ArrayList<>(0);
        }

        String ukrainianCityName = "";
        if(response[0].getLocalNames() != null){
            ukrainianCityName = response[0].getLocalNames().get("uk");
        }

        boolean theSame;
        for(int i = 0; i < response.length;i++){
            theSame = false;
            for(WeatherByCity city: cities){
                if(
                        city.getName().equals(response[i].getName()) &&
                                ((city.getState() == null && response[i].getState() == null) ||
                                        (city.getState() != null && response[i].getState() != null &&
                                                city.getState().equals(response[i].getState())
                                        )
                                ) &&
                                city.getCountryCode().equals(response[i].getCountry())

                ){
                    theSame = true;
                    break;
                }
            }

            if(theSame){
                continue;
            }

            cities.add(weatherByCityRepository.save(
                    new WeatherByCity(
                            response[i].getName(),
                            response[i].getLat(),
                            response[i].getLon(),
                            LocalDate.now().toString(),
                            ukrainianCityName,
                            response[i].getState(),
                            response[i].getCountry()
                    )
            ));
        }

        return cities;
    }


    private List<CityDto> citiesToList(List<WeatherByCity> cities, boolean englishName){
        return cities.stream().map(city ->{
            Locale locale = new  Locale("", city.getCountryCode());
            String countryName;
            countryName = locale.getDisplayName(Locale.ENGLISH);

            if(!englishName) {
                String stateName;
                if(city.getState() != null && city.getState().contains(" ")){
                    stateName = city.getState().substring(0, city.getState().lastIndexOf(' '));
                    List<String> values = weatherByCityRepository.findUkrainianNameByName(stateName);

                    if(!values.isEmpty()){
                        String ukName = values.getFirst();
                        if(ukName != null && city.getUkrainianName() != null && !city.getUkrainianName().isEmpty()){
                            countryName = locale.getDisplayName(ukrainianLocale);
                            city.setState(ukName);
                            city.setName(city.getUkrainianName());
                        }
                    }
                    else {
                        List<WeatherByCity> empty = new  ArrayList<>();
                        List<WeatherByCity> savedCities = applyGeocodingRequest(stateName, empty);

                        if(savedCities != null && !savedCities.isEmpty()){
                            int i = 0;
                            for(; i < savedCities.size();i++){
                                if(savedCities.get(i).getUkrainianName() != null && city.getUkrainianName() != null && !city.getUkrainianName().isEmpty()){
                                    break;
                                }
                            }
                            if(i <  savedCities.size()){
                                String ukName = savedCities.get(i).getUkrainianName();

                                if(ukName != null && city.getUkrainianName() != null){
                                    countryName = locale.getDisplayName(ukrainianLocale);
                                    city.setState(ukName);
                                    city.setName(city.getUkrainianName());

                                }
                            }
                        }

                        writeTypes(savedCities, stateName);
                    }
                }

            }
            if(countryName.equals("Ukraine")){
                countryName = "Україна";
            }

            return new CityDto(
                    city.getName(),
                    countryName,
                    city
            );
        }).toList();
    }


    public SimpleWeatherRequest findByItem(WeatherByCity weatherItem) {
        SimpleWeatherRequest  simpleWeatherRequest = new SimpleWeatherRequest();
        List<HourlyWeather> hourlyWeathers;

        LocalDateTime currentTime = LocalDateTime.now();
        currentTime = currentTime.minusHours(currentTime.getHour()).minusMinutes(currentTime.getMinute()).minusSeconds(currentTime.getSecond());
        weatherByCityRepository.incrementOrderById(weatherItem.getId());

        int itemsToDelete = hourlyWeatherRepository.countWithLessDate(currentTime, weatherItem.getId());
        if(itemsToDelete > 0){
            hourlyWeatherRepository.deleteAllByCityId(weatherItem.getId());
            ZoneTransfer transfer = getHourlyWeatherListForTwoDays(weatherItem, currentTime.plusDays(2));

            simpleWeatherRequest.setHourlyWeather(transfer.hourlyWeatherList);
            simpleWeatherRequest.getHourlyWeather().addAll(getHourlyWeatherListForFiveDays(
                    weatherItem,
                    currentTime.plusDays(2),
                    transfer.timezone
            ));

        }
        else {
            hourlyWeathers = hourlyWeatherRepository.findByCityId(weatherItem.getId());
            if (hourlyWeathers.isEmpty()){
                ZoneTransfer transfer = getHourlyWeatherListForTwoDays( weatherItem, currentTime.plusDays(2));

                simpleWeatherRequest.setHourlyWeather(transfer.hourlyWeatherList);
                simpleWeatherRequest.getHourlyWeather().addAll(getHourlyWeatherListForFiveDays(
                        weatherItem,
                        currentTime.plusDays(2),
                        transfer.timezone
                ));
            }
            else {
                simpleWeatherRequest.setHourlyWeather(hourlyWeathers);
            }
        }

        itemsToDelete = dailyTemperatureRepository.countWithLessDate(currentTime.toLocalDate(), weatherItem.getId());
        if(itemsToDelete > 0){
            dailyTemperatureRepository.deleteAllByCityId( weatherItem.getId());
            simpleWeatherRequest.setDailyTemperature(getDailyWeather( weatherItem));
        }
        else {
            List<DailyTemperature> dailyTemperatures = dailyTemperatureRepository.findByCityId(weatherItem.getId());
            if(dailyTemperatures.isEmpty()){
                simpleWeatherRequest.setDailyTemperature(getDailyWeather( weatherItem));
            }
            else {
                simpleWeatherRequest.setDailyTemperature(dailyTemperatures);
            }
        }
        simpleWeatherRequest.setCityName(weatherItem.getName());
        return simpleWeatherRequest;
    }


    private int[] findUserTypes(String cityName){
        try(RandomAccessFile file = new  RandomAccessFile(new File("userTypes.bin"), "r")){
            if(file.length() > 0){
                byte boxByte;
                byte[] doubleByte = new byte[2];
                byte[] quatroByte = new byte[4];
                byte[] arrayByteBox;
                char[] arrayCharBox;
                int[] arrayIds;

                nextRecord:
                while (file.length() > file.getFilePointer()+1){
                    file.read(doubleByte);

                    boxByte = file.readByte();
                    if(boxByte != cityName.length()){
                        file.skipBytes(transformFromBytesToInt(doubleByte) -1);
                        continue;
                    }
                    arrayByteBox = new byte[boxByte*2];
                    file.read(arrayByteBox);

                    String copy = new String(arrayByteBox);

                    if(!copy.equals(cityName)){
                        file.skipBytes(
                                transformFromBytesToInt(doubleByte) - arrayByteBox.length - 1
                        );
                        continue;
                    }


                    boxByte = file.readByte();
                    arrayIds = new int[boxByte];

                    for(int i = 0; i < boxByte; i++){
                        file.read(quatroByte);
                        arrayIds[i] = transformFromBytesToInt(quatroByte);
                    }

                    return arrayIds;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public List<CityDto> offerCityByName(String cityName) {
        Pageable limitTen = PageRequest.of(0, 10);
        List<WeatherByCity> cities;

        if(cityName.charAt(0) < inAsciCode){
            cities = weatherByCityRepository.offerByName(cityName, limitTen);
        }
        else{
            cities = weatherByCityRepository.offerByUkrainianName(cityName, limitTen);
        }

        if(cities.isEmpty()){
            Optional<WithoutResults> results =  withoutResultsRepository.findFirstByUserType(cityName);
            if(results.isPresent()){
                return new ArrayList<>();
            }
            int[] ids = findUserTypes(cityName);

            if(ids == null){
                List<WeatherByCity> savedCities = applyGeocodingRequest(cityName, cities);

                if(savedCities.isEmpty()){
                    WithoutResults result = new WithoutResults();
                    result.setUserType(cityName);
                    withoutResultsRepository.save(result);
                    return new ArrayList<>();
                }

                writeTypes(savedCities, cityName);
                return citiesToList(savedCities, cityName.charAt(0) < inAsciCode);
            }
            else {
                for(int i = 0; i < ids.length; i++){
                    cities.add(weatherByCityRepository.findById((long)ids[i]).get());
                }
            }

        }

        return citiesToList(cities, cityName.charAt(0) < inAsciCode);
    }


    private List<DailyTemperature> getDailyWeather(WeatherByCity city){
        ResponseForEightDays response = restTemplate.getForObject(
                "https://api.openweathermap.org/data/3.0/onecall?lat=" + city.getLat() +
                        "&lon=" + city.getLon() + "&exclude=current,minutely,hourly,alerts&units=metric&appid="
                        + privateKey,
                ResponseForEightDays.class
        );
        List<DailyTemperature> dailyTemperatureList = new ArrayList<>(response.getDaily().size());

        response.getDaily().forEach(
                weather ->
                        dailyTemperatureList.add(
                                new DailyTemperature(
                                        transformDtToLocalDateTime(weather.getDt(), response.getTimezone()).toLocalDate(),
                                        city.getId(),
                                        transformDtToLocalDateTime(weather.getSunrise(), response.getTimezone()).toLocalTime(),
                                        transformDtToLocalDateTime(weather.getSunset(), response.getTimezone()).toLocalTime(),

                                        weather.getTemp().getMin(),
                                        weather.getTemp().getMax(),
                                        weather.getTemp().getEve(),
                                        weather.getTemp().getNight(),
                                        weather.getTemp().getDay(),
                                        weather.getTemp().getMorn(),

                                        weather.getClouds(),

                                        weather.getFeelsLike().getEve(),
                                        weather.getFeelsLike().getNight(),
                                        weather.getFeelsLike().getDay(),
                                        weather.getFeelsLike().getMorn(),

                                        weather.getWeather().getFirst().getMain(),
                                        weather.getWindDeg(),
                                        weather.getWindSpeed(),
                                        weather.getPop(),
                                        weather.getWeather().get(0).getDescription()
                                )

                        )
        );

        dailyTemperatureRepository.saveAll(dailyTemperatureList);
        return  dailyTemperatureList;
    }


    private List<HourlyWeather> getHourlyWeatherListForFiveDays(WeatherByCity weatherItem, LocalDateTime upToMoment, String timezone) {
        ResponseForFive response = restTemplate.getForObject(

                "https://api.openweathermap.org/data/2.5/forecast?lat="+
                        weatherItem.getLat() + "&lon=" +
                        weatherItem.getLon() +
                        "&units=metric&appid=" + simpleKey,
                ResponseForFive.class
        );

        List<HourlyWeather> hourlyWeatherList = new ArrayList<>(response.getList().size());

        int i = 0;
        String time = upToMoment.toString().substring(0, 10);

        for(;i < response.getList().size() &&
                !response.getList().get(i).getDtTxt().startsWith(time); i++);

        time = upToMoment.plusDays(3).toString().substring(0, 10);
        for(;i < response.getList().size(); i++){
            if(!response.getList().get(i).getDtTxt().startsWith(time)){
                ListItem item = response.getList().get(i);
                hourlyWeatherList.add(new HourlyWeather(
                        item.getMain().getTemp(),
                        LocalDateTime.of(
                                LocalDate.parse(item.getDtTxt().substring(0, 10)),
                                LocalTime.parse(item.getDtTxt().substring(11))
                        ),
                        item.getClouds().getAll(),
                        item.getMain().getFeelsLike(),
                        item.getWeather().getFirst().getMain(),
                        item.getWind().getDeg(),
                        item.getWind().getSpeed(),
                        weatherItem.getId(),
                        item.getPop(),
                        item.getWeather().get(0).getDescription()

                ));
            }
            else break;
        }

        hourlyWeatherRepository.saveAll(hourlyWeatherList);         //  ?   put that statement in this place or in the place of invoking this method
        return hourlyWeatherList;
    }


    private ZoneTransfer getHourlyWeatherListForTwoDays(WeatherByCity weatherItem, LocalDateTime upToMoment) {
        ResponseForTwoDays response = restTemplate.getForObject(

                "https://api.openweathermap.org/data/3.0/onecall?lat="+ weatherItem.getLat()+"&lon="+ weatherItem.getLon() + "&exclude=current,minutely,daily,alerts&units=metric&appid="
                        + privateKey,
                ResponseForTwoDays.class
        );

        List<HourlyWeather> hourlyWeatherList = new ArrayList<>(response.getHourly().size());

        LocalDateTime upMoment = upToMoment.minusDays(1).plusHours(23);
        response.getHourly().stream().filter(
                weather ->
                        transformDtToLocalDateTime(weather.getDt(), response.getTimezone()).isBefore(upMoment)
        ) .forEach(
                weather ->
                        hourlyWeatherList.add(new HourlyWeather(
                                weather.getTemp(),
                                transformDtToLocalDateTime(weather.getDt(), response.getTimezone()),
                                weather.getClouds(),
                                weather.getFeelsLike(),
                                weather.getWeather().getFirst().getMain(),
                                weather.getWindDeg(),
                                weather.getWindSpeed(),
                                weatherItem.getId(),
                                weather.getPop(),
                                weather.getWeather().get(0).getDescription()
                        ))
        ) ;


        hourlyWeatherRepository.saveAll(hourlyWeatherList);         //  ?   put that statement in this place or in the place of invoking this method
        return new ZoneTransfer(hourlyWeatherList, response.getTimezone());
    }


    public SimpleWeatherRequest makeInitialRequest(){
        WeatherByCity city = weatherByCityRepository.findByName("Kyiv");

        return findByItem(city);
    }


    private LocalDateTime transformDtToLocalDateTime(Integer dt, String timeZone){
        Instant instant = Instant.ofEpochSecond(dt);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(timeZone));
        return zonedDateTime.toLocalDateTime();
    }


    private int transformFromBytesToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            if(bytes[i] >= 0){
                result = (result << 8) | bytes[i];
            }
            else {
                result = (result << 8) | (bytes[i] + 256);
            }
        }
        return result;
    }


    private byte[] fromIntToBytes(int numToTransform, int sizeOfArray){
        byte[] bytes = new byte[sizeOfArray];
        for(int i = bytes.length - 1, j = 0; i >= 0; i--, j++){
            bytes[j] = (byte) (numToTransform >> 8*i);
        }

        return bytes;
    }


    private void writeTypes(List<WeatherByCity> cities, String userType){

        try(FileOutputStream outputStream = new FileOutputStream("userTypes.bin", true)){
            byte[] typeInBytes = userType.getBytes();
            int length = 1 + typeInBytes.length + 1 + cities.size()*4;
            outputStream.write(fromIntToBytes(length, 2));
            outputStream.write((byte)typeInBytes.length);

            outputStream.write(typeInBytes);

            outputStream.write((byte)cities.size());

            for(WeatherByCity city: cities){
                outputStream.write(fromIntToBytes((int)city.getId(), 4));
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    private record ZoneTransfer(List<HourlyWeather> hourlyWeatherList, String timezone) {}
}
