package com.monogoaggregate.example.dto.assembler;

import com.monogoaggregate.example.domain.FileInfo;
import com.monogoaggregate.example.dto.FileInfoDto;

public class FileInfoDtoAssembler {

    public static FileInfo disassemble(FileInfoDto fileInfoDto) {
        return new FileInfo(fileInfoDto.getId(), fileInfoDto.getFileName(), fileInfoDto.getFileType());
    }

    public static FileInfoDto assemble(FileInfo fileInfo) {
        return new FileInfoDto(fileInfo.getId(), fileInfo.getFileName(), fileInfo.getFileType());
    }
}
