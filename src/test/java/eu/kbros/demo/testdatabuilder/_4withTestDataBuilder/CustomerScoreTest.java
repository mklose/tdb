package eu.kbros.demo.testdatabuilder._4withTestDataBuilder;

import static eu.kbros.demo.testdatabuilder._4withTestDataBuilder.TestDataBuilderFactory.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CustomerScoreTest {
    private CustomerBuilder aCustomer;

    @Test
    public void the_score_should_be_one_with_a_single_1k_order() {
    	aCustomer = aCustomer()
    		.with(
    				anOrder()
    					.withValue(1000)
    		);
    	
    	assertThat(aCustomer.build().getScore(), is(1));
    	assertThat(aCustomer.getScore(), is(1));
        assertThat(scoreFor(aCustomer), is(1));
        assertThatScoreOf(aCustomer, is(1));
        assertThat(aCustomer, hasScore(1));

    }

    
	private void assertThatScoreOf(CustomerBuilder customer, Matcher<Integer> matcher) {
    	assertThat(customer.build().getScore(), matcher);
	}

	private int scoreFor(CustomerBuilder customer){
    	return customer.build().getScore();
    }

	private Matcher<CustomerBuilder> hasScore(final int score) {
		return new BaseMatcher<CustomerBuilder>() {
			@Override
			public boolean matches(final Object item) {
				return score == getScore(item);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("score should be ").appendValue(score);
			}

			@Override
			public void describeMismatch(final Object item,
					final Description description) {
				description.appendText("was ").appendValue(getScore(item));
			}

			private int getScore(Object item) {
				CustomerBuilder customerBuilder = (CustomerBuilder) item;
				return customerBuilder.build().getScore();
			}
		};
	}
}
