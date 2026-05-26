```text
┏━┓┏━╸╻ ╻┏┳┓┏━┓   ╭─╮╶┬╴╭─╮╷ ╷╭─╴╶┬╴
┃ ┃┃╺┓┣━┫┃┃┃┣━┫   ╰─╮ │ ├┬╯│ ││   │
┗━┛┗━┛╹ ╹╹ ╹╹ ╹   ╰─╯ ╵ ╵╰╴╰─╯╰─╴ ╵
```
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Oghma - менеджер модов, включает: модель, репозиторий, сервис и свой UI.

### Model: см. [OghmaModel.md][1]
> На момент 26.05.2026 19:00 это:
* Conflict - Кофликт между двумя модами
* InstalledMod - Скачанный пользователем мод

### Repository:
> На момент 26.05.2026 19:00 это:
* InstalledModRepository - Работа со скачанными модами

### Service: см. [OghmaService.md][3]
> На момент 26.05.2026 19:00 это:
* InstalledModService - Логика работы со скачанными модами

### UI: см. [OghmaUI.md][4]
> На момент 26.05.2026 19:00 это:

>TODO: Займусь этим когда буду делать интерфейс

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
```text
oghma/
├── model/
│   ├── Conflict.java
│   └── InstalledMod.java
├── repository/
│   └── InstalledModRepository.java
├── service/
│   └── InstalledModService.java
└── ui/
```
[1]: ../Models/OghmaModels.md
[2]: ../Repositories/OghmaRepo.md
[3]: ../Services/OghmaServices.md
