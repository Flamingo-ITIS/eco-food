package ru.itis.flamingo.ecofood.util;

import ru.itis.flamingo.ecofood.domain.dto.BuyDto;

import java.util.Comparator;

public class BuyComporator implements Comparator<BuyDto> {
    @Override
    public int compare(BuyDto o1, BuyDto o2) {
        return (int)(o1.getId() - o2.getId());
    }
}