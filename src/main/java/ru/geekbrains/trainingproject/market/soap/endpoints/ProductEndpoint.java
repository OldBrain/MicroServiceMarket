package ru.geekbrains.trainingproject.market.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.trainingproject.market.soap.GetAllProductsRequest;
import ru.geekbrains.trainingproject.market.soap.GetAllProductsResponse;
import ru.geekbrains.trainingproject.market.dtos.soap.ProductsSoapDto;
import ru.geekbrains.trainingproject.market.services.soap.ProductSoapService;
import ru.geekbrains.trainingproject.market.soap.GetProductByTitleRequest;
import ru.geekbrains.trainingproject.market.soap.GetProductByTitleResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/ws/products";
    private final ProductSoapService productSoapService;

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.flamexander.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:GetAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProduct(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();

        for (ProductsSoapDto ps:productSoapService.getAllProductSoapDto()) {
            response.getProducts().add(ps);
        }
        return response;

    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getStudentByName(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProductsSoapDto(productSoapService.getByName(request.getName()));
        return response;
    }

}
