# Tip Calculator - LPDM

## 1. Descricao do projeto

Aplicativo Android nativo que calcula gorjetas a partir
do valor da conta e do percentual informado pelo usuario.

O aplicativo apresenta campos de entrada, botao para
calcular e exibicao do resultado em reais.

Se o recurso estiver implementado, permite arredondar
a gorjeta utilizando um Switch.

## 2. Tecnologias utilizadas

- Kotlin
- Jetpack Compose
- Material Design 3
- Android Studio (versao: PREENCHER)
- Gradle

## 3. Como compilar e executar

1. Clonar o repositorio pelo Git.
2. Abrir a pasta do projeto no Android Studio.
3. Aguardar a sincronizacao do Gradle.
4. Selecionar um emulador ou dispositivo Android.
5. Clicar em Run para executar o aplicativo.

## 4. Arquivos e componentes principais

- MainActivity.kt: inicializa o aplicativo Android.
- TipTimeLayout(): organiza a tela da calculadora.
- EditNumberField(): campo numerico reutilizavel.
- calcularGorjeta(): calcula e formata o valor.
- RoundTheTipRow(): controla o arredondamento,
  caso esteja implementado.

## 5. Hierarquia dos componentes

MainActivity
  |
  +-- TipCalculatorTheme
       |
       +-- TipTimeLayout
            |
            +-- Column
                 |
                 +-- Text (titulo)
                 +-- EditNumberField (conta)
                 +-- EditNumberField (percentual)
                 +-- RoundTheTipRow (se implementado)
                 |    +-- Row
                 |         +-- Text
                 |         +-- Switch
                 +-- Button (calcular)
                 +-- Text (resultado)

## 6. Identificacao

Aluno: Wendell Teixeira
Turma: Informatica 3º Periodo
Disciplina: LPDM
Professor: Rodrigo de Lima Cunha
