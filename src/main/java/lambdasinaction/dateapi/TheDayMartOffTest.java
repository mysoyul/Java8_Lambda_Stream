package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import static java.time.temporal.TemporalAdjusters.*;

public class TheDayMartOffTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("현재날짜 = " + today);

        //with(TemporalAdjuster) TemporalAdjuster의 추상메서드 Temporal adjustInto(Temporal temporal)
//        today.with(new TemporalAdjuster() {
//            @Override
//            public Temporal adjustInto(Temporal temporal) {
//                return null;
//            }
//        });
        today.with(temporal -> {
           //1. 기준을 되는 날짜를 구하기
            LocalDate theDay = LocalDate.from(temporal);
            //2. 두번째,네번째 일요일 날짜를 구하기
            LocalDate secondDay = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
            LocalDate fourthDay = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SATURDAY));
            //3. 기준날짜와 비교하기

        });


    }
}
