package com.peter.bosta_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.ui.screens.CitiesListScreen
import com.peter.bosta_assessment.cities_list.ui.screens.CitiesListScreenContent
import com.peter.bosta_assessment.ui.theme.BostaAssessmentComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BostaAssessmentComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CitiesListScreen(
                        modifier = Modifier.padding(innerPadding),
                        cityList = City.mockList
                    )
                }
            }
        }
    }
}
