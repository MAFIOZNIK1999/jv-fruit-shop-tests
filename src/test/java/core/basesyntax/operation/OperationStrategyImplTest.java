package core.basesyntax.operation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperationStrategyImplTest {
    private static OperationStrategy operationStrategy;
    private static Map<String, OperationHandler> operationHandlerMap;
    private OperationHandler expected;
    private OperationHandler actual;

    @BeforeClass
    public static void beforeClass() {
        operationHandlerMap = new HashMap<>();
        operationStrategy = new OperationStrategyImpl(operationHandlerMap);
    }

    @Before
    public void setUp() {
        operationHandlerMap.put("b", new BalanceHandler());
        operationHandlerMap.put("s", new IncreaseFruitHandler());
        operationHandlerMap.put("p", new PurchaseHandler());
        operationHandlerMap.put("r", new IncreaseFruitHandler());
    }

    @Test
    public void getHandler_Ok() {
        expected = operationHandlerMap.get("b");
        actual = operationStrategy.get("b");
        expected = operationHandlerMap.get("s");
        actual = operationStrategy.get("s");
        expected = operationHandlerMap.get("p");
        actual = operationStrategy.get("p");
        expected = operationHandlerMap.get("r");
        actual = operationStrategy.get("r");
        assertEquals(expected, actual);
    }

    @Test
    public void getHandler_balanceHandler_Ok() {
        expected = operationHandlerMap.get("b");
        actual = operationStrategy.get("b");
        assertEquals(expected, actual);
    }

    @Test
    public void getHandler_supplyHandler_Ok() {
        expected = operationHandlerMap.get("s");
        actual = operationStrategy.get("s");
        assertEquals(expected, actual);
    }

    @Test
    public void getHandler_returnHandler_Ok() {
        expected = operationHandlerMap.get("r");
        actual = operationStrategy.get("r");
        assertEquals(expected, actual);
    }

    @Test
    public void getHandler_purchaseHandler_Ok() {
        expected = operationHandlerMap.get("p");
        actual = operationStrategy.get("p");
        assertEquals(expected, actual);
    }

    @Test (expected = RuntimeException.class)
    public void getHandler_handlerNull_NotOk() {
        actual = operationStrategy.get(null);
    }
}
