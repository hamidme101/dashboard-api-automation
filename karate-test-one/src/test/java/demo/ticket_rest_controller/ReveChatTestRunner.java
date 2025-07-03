package revechat;

import com.intuit.karate.junit5.Karate;

public class ReveChatTestRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}