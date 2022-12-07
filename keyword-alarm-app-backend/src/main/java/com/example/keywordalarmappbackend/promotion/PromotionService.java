package com.example.keywordalarmappbackend.promotion;

import java.util.List;

public interface PromotionService {
  List<Promotion> getPromotions(long memberId);
}
