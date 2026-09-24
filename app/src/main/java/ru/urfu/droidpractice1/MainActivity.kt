package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.ArticleViewModel

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArticleScreen()
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.i(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.i(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy") }
}

@Composable
fun ArticleScreen(viewModel: ArticleViewModel = viewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val isRead = result.data?.getBooleanExtra("isRead", false) ?: false
            viewModel.isRead = isRead
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Закрытие «О'кей»",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, "Дата закрытия гипермаркетов «О'кей»")
                    putExtra(Intent.EXTRA_TEXT, "Дата закрытия гипермаркетов «О'кей» в Екатеринбурге - 21 сентября. Магазины откроются под вывеской «Ленты» 30 сентября.")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Поделиться статьей"))
            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Поделиться")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "18 сентября 2026", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Absolute.Left) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { viewModel.likes++ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Лайк")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { viewModel.likes-- }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Дизлайк")
                }
                Text(text = "${viewModel.likes}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = "https://n1s1.hsmedia.ru/a0/2d/b4/a02db4dce34ef23aaf184988049b7bd9/656x492_1:5523_1a70fc090cbd872715ba0473f7b49b5b@1280x960_0xQAWVSM8H_2382835442562170852.jpg.webp",
            contentDescription = "Изображение статьи",
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Все три магазина перестанут работать 21 сентября, а через несколько дней, 30 сентября, они откроются под вывеской «Ленты».",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Работы пройдут в гипермаркетах на улицах Академика Шварца, Репина и Бабушкина. За это время в торговых залах обновят оборудование и навигацию.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "«Сотрудники „О’кей“ продолжат работать в „Ленте“. Они хорошо знают своих покупателей, и этот опыт поможет сохранить высокий уровень обслуживания», — рассказал директор торговой сети Руслан Ардовский.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Первый магазин «О’Кей» открылся в Петербурге в 2002 году. На конец 2024-го компания объединяла 301 магазин — 77 одноименных гипермаркетов и 224 дискаунтера «ДА!». До конца 2024 года обе сети принадлежали зарегистрированной в Люксембурге O'KEY Group S.A., основными бенефициарами которой были Дмитрий Троицкий (34,5%) и Борис Волчек (34,1%). До последнего времени сетью владело ООО «Рбф-Ритейл».",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "По данным «Контур.Фокус», выручка ООО «О’Кей» в 2025 году составила 147,9 миллиарда рублей, что на 1% меньше, чем в 2024-м. Убыток составил 45,4 миллиона рублей, в разы увеличившись по сравнению с предыдущим годом.",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Компания «Лента» была основана в 1993 году в Петербурге. Как следует из информации на сайте, под управлением группы находится 268 гипермаркетов, 353 супермаркета, 3 400 магазинов у дома и 1 798 магазинов непродовольственных товаров в более чем 650 населенных пунктах России. Компанию контролируют структуры «Севергрупп» Алексея Мордашова.",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Напомним, в июне июня «Группа Лента» официально объявила о приобретении сети «О'кей» (точнее оператора сети — ООО «РБФ-Ритейл»). В сделку вошли 75 гипермаркетов и четыре арендованных распределительных центра. Новый владелец взял на себя долги приобретенной компании в качестве оплаты за 100% долей.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "В конце августа там уже начали менять оформления торговых залов. Там появились вывески «Распродажа» и опустели полки.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        val buttonColor = if (viewModel.isRead) Color.Green else MaterialTheme.colorScheme.primary

        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra("INITIAL_IS_READ", viewModel.isRead)
                }
                launcher.launch(intent)
              },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(text = if (viewModel.isRead) "Перейти ко второй статье (✓)" else "Перейти ко второй статье")
        }
    }
}