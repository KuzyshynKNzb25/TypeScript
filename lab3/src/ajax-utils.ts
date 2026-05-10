namespace $ajaxUtils {
    function getRequestObject(): XMLHttpRequest | null {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else {
            window.alert("Ajax is not supported!");
            return null;
        }
    }

    export function sendGetRequest(
        requestUrl: string,
        responseHandler: (response: any) => void,
        isJsonResponse: boolean = true
    ): void {
        let request = getRequestObject();
        if (!request) return;

        request.onreadystatechange = function () {
            handleResponse(request, responseHandler, isJsonResponse);
        };
        request.open("GET", requestUrl, true);
        request.send(null);
    }

    function handleResponse(
        request: XMLHttpRequest,
        responseHandler: (response: any) => void,
        isJsonResponse: boolean
    ): void {
        if (request.readyState === 4 && request.status === 200) {
            if (isJsonResponse) {
                responseHandler(JSON.parse(request.responseText));
            } else {
                responseHandler(request.responseText);
            }
        }
    }
}