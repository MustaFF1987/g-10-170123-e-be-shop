package de.telran.g10170123ebeshop.scheduler;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.domain.entity.jpa.Task;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import de.telran.g10170123ebeshop.repository.jpa.TaskRepository;
import de.telran.g10170123ebeshop.service.jpa.TaskService;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {

    @Autowired
    private TaskService service;

    @Autowired
    private ProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        service.save(task);
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task (3 seconds)");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task (7 seconds)");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        Task task = new Task("Fixed rate task (3 seconds)");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        Task task = new Task("Fixed rate task (7 seconds)");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    @Async
//    public void fixedRateAsyncTask() {
//        Task task = new Task("Fixed rate async task (7 seconds)");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
//    public void initialDelayTask() {
//        Task task = new Task("Initial delay task");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // fixedDelay = 7200000 -> PT02H
//    @Scheduled(fixedDelayString = "PT03S")
//    public void anotherDelayFormatTask() {
//        Task task = new Task("Another delay format task");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelayString = "${interval}")
//    public void delayInPropertyTask() {
//        Task task = new Task("Delay in property task");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // 55 * * * * * -> каждую минуту в 55 секунд
    // 0 15 9-17 * * MON-FRI -> в 15 минут каждого часа с 9 до 17 в будние дни
    // 0 0 * * * * -> @hourly -> каждый час
//    @Scheduled(cron = "${cron-interval}")
//    public void cronExpressionTask() {
//        Task task = new Task("Cron expression task");
//        logger.info(task.getDescription());
//        service.save(task);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void executeTask(Task task) {
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        // Такой вариант запускает задачу после определённого события по расписанию
//        scheduler.schedule(() -> logger.info(task.getDescription()),
//                new CronTrigger("0,10,20,30,40,50 * * * * *"));

        // Такой вариант запускает задачу после определённого события разово
        logger.info("Method executeTask called");
        Instant instant = Instant.now().plusSeconds(20);
        scheduler.schedule(() -> logger.info(task.getDescription()), instant);
    }

// ===============================================================================

    //Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
    //   Время выполнения предыдущей задачи не должно влиять на старт следующей.

//    @Scheduled(fixedRateString = "PT30S") // Каждые 30 секунд
//    public void displayLastFiveTasks() {
//        service.displayLastFiveTasks();
//        logger.info("Метод удачно вызван");
//    }
//
//==============================================================================

    //Реализовать вывод в консоль последнего добавленного в БД товара.
    //Вывод должен производиться в 15 и 45 секунд каждой минуты.
    //Задача должна быть сохранена в БД.
    //Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
    //Пример значения поля description - "Последний добавленный в БД продукт - Банан

    @Scheduled(cron = "5,10,15,12,25,30,35,40,45,50,55 * * * * *")
    public void displayLastAddedProduct() {
        Product lastProduct = null;
        lastProduct = (Product) productRepository.findTopByNameOrderByTimestampDesc(lastProduct.getName());
        if (lastProduct != null) {
            String productName = "Последний добавленный в БД продукт - " + lastProduct.getName();
            service.save(new Task(productName));
            logger.info(productName);
        } else {
            logger.info("Продуктов не найдено");
        }
    }

}

//==============================================================================

// После запроса конкретного продукта через 15 секунд отправить персональное предложение
//на этот продукт с ценой на 5-10% (рандомно) ниже, чем базовая цена.
//Имитировать отправку в виде вывода в консоль и логирования.
//Данная задача должна выполняться при помощи АОП и сохраняться в БД.
//Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
//Поле description задачи должно содержать предложение купить товар по новой цене
//с указанием наименования товара, старой цены и новой цены.
//Указание скидки для данного предложения не должно влиять на базовую цену товара в БД.


//    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.getById(..))")
//    public void getById() {
//    }
//    @AfterReturning(value = "getById()", returning = "product")
//    public void sendOffer(Product product) {
//        if (product != null) {
//            TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//            scheduler.schedule(() -> {
//                double discount = generateRandomDiscount();
//                double newPrice = product.getPrice() * (1 - discount);
//                String description = "Предложение для " + product.getName() +
//                        ": Купите товар по специальной цене " + newPrice + " (старая цена: " + product.getPrice() + ").";
//                service.save(new Task(description));
//                logger.info(description);
//            }, Instant.now().plusSeconds(1));
//        }
//    }
//    private double generateRandomDiscount() {
//        return 0.05 + Math.random() * 0.05; // Генерируем случайную скидку от 5% до 10%
//    }
//}

//================================================================================================

    // Пока эту задачу не выполнил

//После того как покупатель очистил корзину, через 20 секунд выбрать из корзины случайный товар,
//который там был, сделать на него скидку 15% и предложить покупателю всё-таки
//приобрести все эти товары, вывести все товары (один с новой ценой), а также старую и новую стоимость корзины.
//Данная задача должна выполняться при помощи АОП и сохраняться в БД.
//Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
//Поле description задачи должно содержать предложение приобрести все товары из корзины,
//список товаров из корзины (каждый товар с новой строки), причём товар со скидкой должен быть указан с новой ценой,
//а также с новой строки - старую стоимость корзины и новую стоимость корзины.
//Указание скидки для данного предложения не должно влиять на базовую цену товара в БД.

