package com.ZenPack.service.Services;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.ZenPack;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZenPackService {
    ResponseEntity<ZenPack> saveZenPack(ZenPack zenPack);

    ResponseEntity<ZenPackDto> createZenPack(ZenPackDto createDto);

    List<ZenPack> getAllZenpack();
}
