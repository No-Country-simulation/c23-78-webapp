package com.trackmyfix.trackmyfix.configs.data;

import com.trackmyfix.trackmyfix.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.trackmyfix.trackmyfix.entity.Type;

import java.util.List;

@Component
@AllArgsConstructor
public class TypeDataLoader {
    private final TypeRepository typeRepository;

    public void load(){
        Type type1 = new Type(null,"MONITOR");
        Type type2 = new Type(null,"TV");
        Type type3 = new Type(null,"CPU");
        Type type4 = new Type(null,"PLACA DE VIDEO");
        Type type5 = new Type(null,"TABLET");

        typeRepository.saveAll(List.of(type1,type2,type3,type4,type5));
    }
}
