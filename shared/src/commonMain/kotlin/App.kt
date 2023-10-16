
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import br.com.jovemtranquilao.Utils
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
@Composable
fun App() {
    MaterialTheme {

        //TODO: descobrir o ddd pela geolocalizacao do dispositivo e colocar automaticamente o DDD
        var telefone: String by remember { mutableStateOf("+55 (11) ") }

        var utils = Utils();

        Column(
        ) {
            val focusRequester = remember { FocusRequester() }

            var keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                ,
                value = telefone,
                label = { Text("Telefone") },
                onValueChange = {
                    var limpo = utils.limpar(it)

                    telefone = utils.mascara(limpo, telefone, it)

                },


                keyboardOptions = KeyboardOptions.Default.copy(

                    keyboardType = KeyboardType.NumberPassword
                ),


            )

            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()

                onDispose {
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                }
            }

            Row(
            ) {
                Button(onClick = {/*
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Hello Swapz")
                        putExtra("jid", utils.limpar(telefone) + "@s.whatsapp.net")
                        type = "text/plain"
                        setPackage("com.whatsapp")
                    }

                    try {
                        context.startActivity(sendIntent)
                    } catch (ex: ActivityNotFoundException) {
                        sendIntent.setPackage(null)
                        context.startActivity(sendIntent)
                    }
                */
                }) { Text("Whatsapp") }

                Button(onClick = {/*
                    val urlString = "http://wa.me/" + utils.limpar(telefone)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setPackage("com.android.chrome")

                    try {
                        context.startActivity(intent)
                    } catch (ex: ActivityNotFoundException) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null)
                        context.startActivity(intent)
                    }
                */
                }) { Text("Crome") }
            }
        }
    }
}

expect fun getPlatformName(): String