package ru.timutkin.secondTask.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Employee  {

    String id;

    String name;

    String surname;

    String patronymic;

    String position;


}
