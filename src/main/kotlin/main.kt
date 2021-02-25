import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import okhttp3.*
import java.io.IOException
import java.nio.charset.StandardCharsets

fun main() = Window {
    var text by remember { mutableStateOf("request url") }

    MaterialTheme {
        Button(onClick = {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://www.github.com")
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    text = String(response.body!!.bytes(), StandardCharsets.UTF_8)
                }
            })

        }) {
            Text(text)
        }
    }
}