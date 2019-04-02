package br.com.matthaus.senewsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.matthaus.senewsapp.features.articles.ArticlesActivity
import br.com.matthaus.senewsapp.features.sources.SourcesActivity
import br.com.matthaus.senewsapp.features.sources.adapters.SourcesListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SourceActivityTest {

    @get:Rule
    var activityRule: IntentsTestRule<SourcesActivity> = IntentsTestRule(SourcesActivity::class.java)

    @Test
    fun should_open_articles_when_click_over_source() {
        onView(withId(R.id.sources_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<SourcesListAdapter.SourcesListAdapterViewHolder>(
                    0, click()
                )
            );

        intended(hasComponent(ArticlesActivity::class.java.name))
    }

}