```text
╻ ╻┏━┓┏━┓╻ ╻┏━╸┏━┓   ╭─╮╶┬╴╭─╮╷ ╷╭─╴╶┬╴
┏╋┛┣━┫┣┳┛┏╋┛┣╸ ┗━┓   ╰─╮ │ ├┬╯│ ││   │
╹ ╹╹ ╹╹┗╸╹ ╹┗━╸┗━┛   ╰─╯ ╵ ╵╰╴╰─╯╰─╴ ╵
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Xarxes - ядро, включает: common, config, Launcher(Main)

### common содержит всё необходимое для общей работы с данными в частности:

* /core - Operation Result и LogService


* /model - модели данных (User, Role, Theme), см. [XarxesModel.md][1]


* /repository - репозитории для работы с данными (UserRepository, FileRepository)


* /service - сервисы для работы с данными, см. [XarxesServices.md][3]
> На момент 26.05.2026 19:00 это:
*
    * AuthService - авторизация и регистрация
*
    * NavigationService - навигационный сервиc
*
    * ReportService - отчеты в pdf (Требование курсовой)
*
    * SessionService - Текущая сессия пользователя.
*
    * UserService - Логика работа c сущностью юзер


* /utils - утилиты для работы со своими данными
> На момент 26.05.2026 19:00 это:
*
    * DateUtils - форматтер дат для красивого отображения
*
    * ImageUtils - Утилита кодирования и декодирвоания изображений в Base64
*
    * JsonUtils - работа с GSON
*
    * PasswordUtils - Хэширование и проверка пароля BCrypt
*
    * TomlUtils - Работа TOML
*
    * ValidatorUtils - Валидатор данных (логин, пароль, и т.д)

### config конфигурация приложения и запуск сервера, в частности:

* AppContext - че то там про единоразовую инициализацию всех сервисов, а после передачи кому нужно
* BootService - Проверяет систему при запуске, пока только проверяет БД и в случае отсутствия создает её
* ConfigService - Загружает настройки из config.properties
* Debug - личная штука для тестов, создает тестовые данные в БД

### UI - Интерфейс самого приложения:
> TODO: Займусь этим когда буду делать интерфейс

```txt
xarxes/
├── common/
│   ├── core/
│   │   ├── LogService.java
│   │   └── OperationResult.java
│   ├── model/
│   │   ├── Role.java
│   │   ├── Theme.java
│   │   └── User.java
│   ├── repository/
│   │   ├── FileRepository.java
│   │   └── UserRepository.java
│   ├── service/
│   │   ├── AuthService.java
│   │   ├── NavigationService.java
│   │   ├── ReportService.java
│   │   ├── SessionService.java
│   │   └── UserService.java
│   └── utils/
│       ├── DateUtils.java
│       ├── ImageUtils.java
│       ├── JsonUtils.java
│       ├── PasswordUtils.java
│       ├── TomlUtils.java
│       └── ValidatorUtils.java
├── config/
│   ├── AppContext.java
│   ├── BootService.java
│   ├── ConfigService.java
│   └── DebugService.java
├── ui/
├── Launcher.java
└── Main.java
```

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

[1]: ../Models/XarxesModels.md

[2]: ../Repositories/XarxesRepo.md

[3]: ../Services/XarxesServices.md
