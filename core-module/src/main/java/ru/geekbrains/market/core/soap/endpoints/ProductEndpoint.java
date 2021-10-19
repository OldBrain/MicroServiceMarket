package ru.geekbrains.market.core.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.market.api.dtos.soap.ProductsSoapDto;
import ru.geekbrains.market.core.soap.GetAllProductsRequest;
import ru.geekbrains.market.core.soap.GetAllProductsResponse;
import ru.geekbrains.market.core.soap.GetProductByTitleRequest;
import ru.geekbrains.market.core.soap.GetProductByTitleResponse;
import ru.geekbrains.market.core.services.soap.ProductSoapService;

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
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */


//*Пример запроса: POST http://localhost:8189/market/ws

// <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
//        xmlns:f="http://www.flamexander.com/spring/ws/products">
//    <soapenv:Header/>
//            <soapenv:Body>
//              <f:getProductByTitleRequest>
//     <f:name>bread</f:name>
//</f:getProductByTitleRequest>
//   </soapenv:Body>
//</soapenv:Envelope>
//
// */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProduct(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();

        for (ProductsSoapDto ps : productSoapService.getAllProductSoapDto()) {
            response.getProducts().add(ps);
        }
        return response;

    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitle(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productSoapService.getByTitle(request.getName()));
        return response;
    }

}
