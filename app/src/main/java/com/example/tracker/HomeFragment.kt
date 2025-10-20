package com.example.tracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import androidx.core.graphics.toColorInt
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val donutChart = view.findViewById<PieChart>(R.id.donutChart)
        createDonutChart(donutChart)

        val barChart = view.findViewById< BarChart>(R.id.barChart)
        createBarChart(barChart)

        val greetings = view.findViewById<TextView>(R.id.greetings)

        greetings.setOnClickListener {
            val loginPage = Intent(requireContext(), MainActivity:: class.java)
            startActivity(loginPage)
        }


    }

    private fun createBarChart(barChart: BarChart) {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 9f)) // X = 0, Y = 5
        entries.add(BarEntry(1f, 10f))
        entries.add(BarEntry(2f, 8f))
        entries.add(BarEntry(3f, 7f))

        val dataSet = BarDataSet(entries, null)
        dataSet.colors = listOf(
            "#2fdc83".toColorInt(),
            "#08d46c".toColorInt(),
            "#56e49a".toColorInt(),
            "#7eecb1".toColorInt()
        )
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f


        val barData = BarData(dataSet)
        barData.barWidth = 0.6f // thickness

        barChart.data = barData
        barChart.description.isEnabled = false
        barChart.setFitBars(true) // make the x-axis fit the bars
        barChart.xAxis.setDrawGridLines(false)
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)

        barChart.xAxis.setDrawAxisLine(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.legend.isEnabled = false

        val labels = listOf("Dog", "Cat", "Bird", "Rabbit")
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)

        barChart.axisRight.isEnabled = false

        barChart.invalidate()
    }

    private fun createDonutChart (donutChart: PieChart) {
        val entries = listOf(
            PieEntry(40f, "Healthy"),
            PieEntry(30f, "Minor Issues"),
            PieEntry(20f, "Serious Issues"),
            PieEntry(10f, "Critical")
        )

        val legend = donutChart.legend
        legend.isEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        val dataSet = PieDataSet(entries, "")
//        dataSet.valueTextColor = Color.WHITE
//        dataSet.valueTextSize = 14f
        dataSet.setDrawValues(false)

        val data = PieData(dataSet)

        donutChart.data = data
        donutChart.setUsePercentValues(true)
        donutChart.description.isEnabled = false
        donutChart.isRotationEnabled = false
        donutChart.setDrawEntryLabels(false)

        donutChart.isDrawHoleEnabled = true
        donutChart.holeRadius = 55f
        donutChart.transparentCircleRadius = 60f
        donutChart.setHoleColor(Color.WHITE)
        donutChart.transparentCircleRadius = 0f

//        donutChart.centerText = "Pet Status"
//        donutChart.setCenterTextSize(16f)

        dataSet.colors = listOf(
            "#08D46C".toColorInt(), // base green
            "#f4e04d".toColorInt(), // yellow
            "#f5a94d".toColorInt(), // orange
            "#f45b5b".toColorInt(), // red
        )

        donutChart.invalidate() // refresh
    }

}