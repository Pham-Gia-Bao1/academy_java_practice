package vn.techzen.academy_pnv_12.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
     int id;
     String name;
     int score;
}
