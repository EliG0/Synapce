```text
╻ ╻┏━╸┏━┓┏┳┓┏━┓┏━╸╻ ╻┏━┓   ╭─╮╶┬╴╭─╮╷ ╷╭─╴╶┬╴
┣━┫┣╸ ┣┳┛┃┃┃┣━┫┣╸ ┃ ┃┗━┓   ╰─╮ │ ├┬╯│ ││   │
╹ ╹┗━╸╹┗╸╹ ╹╹ ╹┗━╸┗━┛┗━┛   ╰─╯ ╵ ╵╰╴╰─╯╰─╴ ╵ 
```
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Hermaeus - каталог модов и интеграция с играми, включает: модели, репозитории, сервисы и свой UI.

### Model: см. [HermaeusModels.md][1]
> На момент 26.05.2026 19:00 это:
> 
* Game — Игра 

* Mod — Модификация

* ModCategory — Категории модов

* ModStatus — Статус мода 

* ModSummary — Краткая сводка/информация о моде для списков каталога

### Repository:
> На момент 26.05.2026 19:00 это:
* GameRepository — Работа с сохраненными играми
* ModRepository — Получение и кэширование данных о модах из репозитория

### Service: см. [HermaeusServices.md][3]

> На момент 26.05.2026 19:00 это:

* GameService — Логика управления игр

* ModService — Логика управления модификациями в Хермеусе

* SearchService — Логика поиска в Хермеусе

### UI:

>TODO: Займусь этим когда буду делать интерфейс


- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
```text
hermaeus/
├── model/
│   ├── Game.java
│   ├── Mod.java
│   ├── ModCategory.java
│   ├── ModStatus.java
│   └── ModSummary.java
├── repository/
│   ├── GameRepository.java
│   └── ModRepository.java
├── service/
│   ├── GameService.java
│   ├── ModService.java
│   └── SearchService.java
└── ui/
```

[1]: ../Models/HermaeusModels.md
[2]: ../Repositories/HermaeusRepo.md
[3]: ../Services/HermaeusServices.md
