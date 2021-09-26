package ru.geekbrains.trainingproject.market.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.trainingproject.market.services.ProductService;
import ru.geekbrains.trainingproject.market.soap.GetAllProductsRequest;
import ru.geekbrains.trainingproject.market.soap.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/ws/students";
    private final ProductService productService;

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
//    @ResponsePayload
//    public GetStudentByNameResponse getStudentByName(@RequestPayload GetStudentByNameRequest request) {
//        GetStudentByNameResponse response = new GetStudentByNameResponse();
//        response.setStudent(studentService.getByName(request.getName()));
//        return response;
//    }

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllStudentsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllProductsRequest getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().forEach(response.getProduct():add);
        return response;
    }
}
