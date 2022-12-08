package com.example.interpolreports.service.impl;

import com.example.interpolreports.model.Notice;
import com.example.interpolreports.model.Person;
import com.example.interpolreports.service.RequestService;
import com.example.interpolreports.service.component.PersonMapper;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private OkHttpClient httpClient;
    private PersonMapper personMapper;

    @Override
    public List<Person> doRequest(Notice notice) {
        Request request = new Request.Builder()
                .url("https://ws-public.interpol.int/notices/v1/" + notice.name())
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()){
            return personMapper.map(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException("Can't read a resource", e);
        }
    }
}
