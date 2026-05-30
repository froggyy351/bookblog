package com.froggyy351.bookblog.Book.dto;

import java.util.List;

public record VolumeInfoDto(String title, List<String> authors, ImageLinksDto imageLinks){}
