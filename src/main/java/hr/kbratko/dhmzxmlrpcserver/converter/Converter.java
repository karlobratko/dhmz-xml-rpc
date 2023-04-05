package hr.kbratko.dhmzxmlrpcserver.converter;

import lombok.NonNull;

public interface Converter<S, T> {

  T convert(@NonNull S source);

}
