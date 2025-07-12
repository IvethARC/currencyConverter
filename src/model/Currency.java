package model;

public record Currency(String time_last_update_utc,
                       String base_code,
                       String target_code,
                       double conversion_rate,
                       double conversion_result
                            ) {}
