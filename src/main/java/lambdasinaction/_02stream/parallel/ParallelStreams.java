package lambdasinaction._02stream.parallel;

import java.util.stream.*;

public class ParallelStreams {

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    //iterate()사용한 순차스트림 : 크기가 고정되지 않음, Boxing 발생함 192 msecs
    public static long sequentialSum(long n) {

        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(Long::sum)
                .get();
    }

    //iterate()사용한 병렬스트림 : 크기가 고정되지 않음, Boxing 발생함 181 msecs
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(Long::sum)
                .get();
    }

    //rangeClosed()사용한 순차스트림 : 크기가 고정됨, Boxing 발생하지 않음 5 msecs
    public static long rangedSum(long n) {
//        return LongStream
//                .rangeClosed(1, n)
//                .reduce(Long::sum).getAsLong();
        return LongStream
                .rangeClosed(1, n)
                .sum();
    }

    //rangeClosed()사용한 병렬스트림 : 크기가 고정됨, Boxing 발생하지 않음 1 msecs
    public static long parallelRangedSum(long n) {
//        return LongStream
//                .rangeClosed(1, n)
//                .parallel()
//                .reduce(Long::sum)
//                .getAsLong();
        return LongStream
                .rangeClosed(1, n)
                .parallel()
                .sum();
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
