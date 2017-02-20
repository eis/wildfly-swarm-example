package example.counter;


import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class CounterService {
    public CounterResult count(CounterRequest counterRequest) {
        return new CounterResult(
                counterRequest.getInt1() + counterRequest.getInt2());
    }
}
