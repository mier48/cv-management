package com.albertomier.cv_management.core.utils

import android.util.Log
import android.util.Patterns
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

object Utils {

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4

    fun enableRegister(name: String, email: String, password: String, confirmPassword: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4 && password == confirmPassword && name.length > 3

    fun getAuthorization(): String {
        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiYmNhZDU1OTFhOWQ2YTY4OTJiZTFhNDEwMDM5MjUwYjIxMmQyOGJiZTA3OGE4ZDE5YWM0N2Y5MjEyZDJhMzQwZDRjMGE2MDg1NTA2NjAyYzEiLCJpYXQiOjE2ODcyNDg1NzcuMzE2OTI4LCJuYmYiOjE2ODcyNDg1NzcuMzE2OTMzLCJleHAiOjE3MTg4NzA5NzcuMzE0MjI5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.VEqi7_bXoe0OTEElHW6oF4l0Bi9F_S2lfOaD9ypKkmmsq8v9_0VkraU81Bw6PBSvspx5DJc9fw2RfpnxgvFF69REOckYB9qYxSgYlVPSaLuGj8q9ySNbBCDCtT8wmI7piIg7MF6ufmKgnTgKl4-LGqNfFizFUz9PdRDJPD-TDdSZEirx-MlBVQd1HH9xsHfF0kyALdFCcMaLOtX8NbF876LPd2sWvfhpmugOPW60Fd8s9vonqo4AE0_D5QuigmkzuxKqy-Aj-n43df5gpDtS6QnUCbHY2jkXVIeI6KacbHXva2L0ElhaVGQwO1NAHLEUtrcxu9tHiFB5MdbQxkxybsWhMWp5XApedCKzKvW-0qz36vD_MiHSinT6Av11AlbSk8sIPu7AxTwJCCbn6w2TB_-HzsLDPRaunsuAJidvhmN6Cq1mGUmbfMBVm9SKyDjvkAjR-6d2B5ZT2ibHAz958joPXGlu0yhp4LoI5q3TOtLJLVc2vfLYrvQF-Sh1cng5Mc_2PCElpCzmQp2LttfODZvHlFsI9bM2Yhum0nXBJd0DLX9mHT4ch7YpyYhM91sgLy9IezPgmsISCCMsZAjfUnNktyeHEIyF-ZRsp3b43pX_02NAs9ddOdxHLGPeXOjIutUVfM3VFixTRhONv6m0t2opPkDjwSs5EsahZmC8VhQ"
        Log.e("TAG", token)
        return "Bearer $token"
    }

    fun customShape() =  object : Shape {
        override fun createOutline(
            size: androidx.compose.ui.geometry.Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            return Outline.Rectangle(Rect(0f,0f,100f /* width */, 131f /* height */))
        }
    }
}