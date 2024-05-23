package themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val lightTheme = lightColors(
    primary = Color(0xFF6200EE),       // Azul roxo para elementos principais
    primaryVariant = Color(0xFF3700B3), // Variante mais escura do azul roxo
    secondary = Color(0xFFFF5722),     // Laranja para destaques
    secondaryVariant = Color(0xFFE64A19), // Variante mais escura do laranja
    surface = Color(0xFFFFFFFF),       // Branco para superfícies
    onPrimary = Color(0xFFFFFFFF),     // Branco no texto de botões e elementos principais
    onSecondary = Color(0xFFFFFFFF),   // Branco no texto de elementos secundários
    onSurface = Color(0xFF000000),     // Preto no texto de superfícies
    background = Color(0xFFF5F5F5),    // Cinza claro para o fundo
    onBackground = Color(0xFF000000),  // Preto no texto de fundo
    error = Color(0xFFB00020),         // Vermelho para erros
    onError = Color(0xFFFFFFFF)        // Branco no texto de erros
)

val darkTheme = darkColors(
    primary = Color(0xFFBB86FC),       // Azul claro para elementos principais
    primaryVariant = Color(0xFF3700B3), // Variante mais escura do azul claro
    secondary = Color(0xFF03DAC6),     // Ciano para destaques
    secondaryVariant = Color(0xFF018786), // Variante mais escura do ciano
    surface = Color(0xFF121212),       // Preto para superfícies
    onPrimary = Color(0xFF000000),     // Preto no texto de botões e elementos principais
    onSecondary = Color(0xFF000000),   // Preto no texto de elementos secundários
    onSurface = Color(0xFFFFFFFF),     // Branco no texto de superfícies
    background = Color(0xFF121212),    // Preto para o fundo
    onBackground = Color(0xFFFFFFFF),  // Branco no texto de fundo
    error = Color(0xFFCF6679),         // Vermelho claro para erros
    onError = Color(0xFF000000)        // Preto no texto de erros
)