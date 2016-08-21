# Capacitação UFCG
Projeto Android com code samples exibidos durante a aula.

Clone the repository:
```shell
$ git clone https://github.com/rogersdk/capacitacao-ufcg.git
```

Comandos básicos adb

## Monkey
adb shell monkey -p <com.seuapp> --throttle 1000 -s 123 -v 10
* -p aplicação
* --throttle delay entre eventos
* -s seed (útil quando quer refazer os mesmos eventos)
* -v nível de verbosidade
* 10 quantidade de eventos

