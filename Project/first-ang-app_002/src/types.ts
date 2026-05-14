interface Weather {
    id: number;
    cloudiness: number;
    sky: string;
    windDegree: number;
    windSpeed: number;

    pop: number;
    description: string
}

export interface DailyTemperature extends Weather {
    date: string;
    sunrise: string;
    sunset: string;

    minTemperature: number;
    maxTemperature: number;
    eveningTemperature: number;
    nightTemperature: number;
    dayTemperature: number;
    morningTemperature: number;


    feelsLikeEvening: number;
    feelsLikeNight: number;
    feelsLikeDay: number;
    feelsLikeMorning: number;

}


export interface HourlyTemperature extends Weather{
    temperature: number;
    time: string;
    feelsLike: number;
    digitTime: Date;
}


export interface WeatherRequest {
    cityName: string;
    hourlyWeather: HourlyTemperature[];
    dailyTemperature: DailyTemperature[];
}


interface CiteHide {
    id: number;
    lat: number;
    lon: number;
    ukrainianName: string;
    state: string;

}

export interface City {
    cityName: string;
    countryName: string;
    city: CiteHide;
}

export const  cloudCase: string[] = ["clear.png", "almost_clear.png", "cloud.png", "full_cloud.png", "rain_light.png","rain_middle.png","rain_snow.png", "light_snow.png", "thunder.png", "snowy.png"];


export const chooseWeather = (weather: Weather): number => {  
    
    if (weather.description === 'light rain') {
        return 4;
    }
    else if (weather.description === 'moderate rain') {
        return 5;
    }
    else if (weather.description === 'rain and snow') {
        return 6;
    }
    else if (weather.description === 'light snow') {
        console.log("it is working");
        return 7;
    }
    else if (weather.cloudiness < 15) {
      return 0;
    }
    else if(weather.cloudiness < 35) {
      return 1;
    }
    else if(weather.cloudiness < 80) {
      return 2;
    }
    else {
      return 3;
    }    
}

export const putCorrectPop = (weather: Weather) => {
    if (weather.pop <= 1) {
         if (weather.pop < 0.2 &&
          weather.description === 'light rain'
        ) {
          weather.pop = Math.floor(Math.random() * 40) + 25;
        }
        else if (weather.pop > 0.6 &&
          weather.sky !== 'Rain'          
        ) {
          weather.description = 'light rain';
          weather.pop =
            Math.round(weather.pop * 100);
        }
        else {
          weather.pop =
            Math.round(weather.pop * 100);
    }
    }
}