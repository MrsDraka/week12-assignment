import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
            .isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	private static Stream<Arguments> argumentsForAddPositive() {
		
		
		
		return Stream.of(
				arguments(2, 4, 6, false),
                arguments(0, 0, 0, true),
                arguments(-2, 3, 1, true),
                arguments(2, -3, -1, true),
                arguments(-2, -3, -5, true),
                arguments(2, 0, 2, true),
                arguments(0, 2, 2, true),
                arguments(-2, 0, -2, true),
                arguments(0, -2, -2, true),
                arguments(2, 8, 10, false),
                arguments(1, 3, 4, false),
                arguments(5, 2, 7, false)
                );
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);

	}

}
