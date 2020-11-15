package com.javawebinar.webapp;

import com.javawebinar.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Григорий Кислин");
        AbstractSection objective1 = new SimpleTextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");
        AbstractSection personal1 = new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        List<String> listAchievements = new ArrayList<>();
        listAchievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX)." +
                " Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listAchievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listAchievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
                " Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        listAchievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        listAchievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        listAchievements.add("Реализация протоколов по приему платежей всех основных платежных системы России" +
                " (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        AbstractSection achievements1 = new BulletedListSection(listAchievements);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        listQualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        listQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        listQualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        listQualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, " +
                "Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        listQualifications.add("Python: Django.");
        listQualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        listQualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        listQualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX," +
                " DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, " +
                "CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        listQualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        listQualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        listQualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        listQualifications.add("Родной русский, английский \"upper intermediate\"");
        AbstractSection qualifications1 = new BulletedListSection(listQualifications);


        List<Organization> organizations = new ArrayList<>();
        Organization org1 = new Organization("Java Online Projects", LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта.\n Создание, организация и проведение Java онлайн проектов и стажировок.",
                "Java Online Projects" ,"https://javaops.ru/");
        Organization org2 = new Organization("Wrike", LocalDate.of(2014, 4, 1), LocalDate.of(2016, 1, 1),
                "Проектирование и разработка онлайн платформы управления проектами" +
                        " Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.", "Wrike","www.wrike.com//");
        Organization org3 = new Organization("RIT Center", LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1), "Организация процесса разработки системы ERP" +
                " для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, " +
                "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
                "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                "Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python", "RIT Center", "link");
        Organization org4 = new Organization("Luxoft (Deutsche Bank)", LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1), "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, " +
                "Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для " +
                "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, " +
                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.", "Luxoft" , "https://luxoft.ru");
        Organization org5 = new Organization("Yota", LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1), "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)", "Yota" ,"https://www.yota.ru/");
        Organization org6 = new Organization("Enkata", LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1), "\tРазработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)" +
                " частей кластерного J2EE приложения (OLAP, Data mining).", "Enkata","http://enkata.com/");
        Organization org7 = new Organization("Siemens AG", LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1), "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, " +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).",
                "Siemens AG" ,"https://new.siemens.com/ru/ru.html");
        Organization org8 = new Organization("Alcatel", LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                "Alcatel", "http://www.alcatel.ru/");
        Organization org9 = new Organization("Coursera", LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1), "\"Functional Programming Principles" +
                " in Scala\" by Martin Odersky", "Coursera","https://www.coursera.org/");
        Organization org10 = new Organization("Luxoft", LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.\"", "Luxoft", "https://luxoft.ru");
        Organization org11 = new Organization("Siemens AG", LocalDate.of(2005, 1, 1),
                LocalDate.of(2015, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)",
                "Siemens AG", "https://new.siemens.com/ru/ru.html");
        Organization org12 = new Organization("Alcatel", LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)",
                "Alcatel","http://www.alcatel.ru/");
        Organization org13 = new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)",
                "Санкт-Петербургский национальный исследовательский университет ","https://itmo.ru/ru/");
        Organization org14 = new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)",
                "Санкт-Петербургский национальный исследовательский университет ", "https://itmo.ru/ru/");
        Organization org15 = new Organization("Заочная физико-техническая школа при МФТИ",
                LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1),
                "Закончил с отличием", "Заочная физико-техническая школа при МФТИ","http://www.school.mipt.ru/");
        organizations.add(org1);
        organizations.add(org2);
        organizations.add(org3);
        organizations.add(org4);
        organizations.add(org5);
        organizations.add(org6);
        organizations.add(org7);
        organizations.add(org8);
        organizations.add(org9);

        List<Organization> eduList = new ArrayList<>();

        eduList.add(org10);
        eduList.add(org11);
        eduList.add(org12);
        eduList.add(org13);
        eduList.add(org14);
        eduList.add(org15);

        AbstractSection work1 = new OrganizationSection(organizations);
        AbstractSection edu1 = new OrganizationSection(eduList);

        String contact1 = "+7(921) 855-0482"; //phone
        String contact2 = "grigory.kislin"; //skype
        String contact3 = "gkislin@yandex.ru"; //email
        String contact4 = "linkedin: https://www.linkedin.com/in/gkislin"; //linkedin
        String contact5 = "github: https://github.com/gkislin"; //github
        String contact6 = "stackoverflow: https://stackoverflow.com/users/548473/grigory-kislin"; //stackoverflow
        String contact7 = "http://gkislin.ru/";//homepage

        r1.setContact(ContactType.PHONE, contact1);
        r1.setContact(ContactType.SKYPE, contact2);
        r1.setContact(ContactType.EMAIL, contact3);
        r1.setContact(ContactType.LINKEDIN, contact4);
        r1.setContact(ContactType.GITHUB, contact5);
        r1.setContact(ContactType.STACKOVERFLOW, contact6);
        r1.setContact(ContactType.HOMEPAGE, contact7);

        r1.setSection(SectionType.PERSONAL, personal1);
        r1.setSection(SectionType.OBJECTIVE, objective1);
        r1.setSection(SectionType.ACHIEVEMENT, achievements1);
        r1.setSection(SectionType.QUALIFICATIONS, qualifications1);
        r1.setSection(SectionType.EXPERIENCE, work1);
        r1.setSection(SectionType.EDUCATION, edu1);


        System.out.println(r1.getContact(ContactType.PHONE));
        System.out.println(r1.getContact(ContactType.SKYPE));
        System.out.println(r1.getContact(ContactType.EMAIL));
        System.out.println(r1.getContact(ContactType.LINKEDIN));
        System.out.println(r1.getContact(ContactType.GITHUB));
        System.out.println(r1.getContact(ContactType.STACKOVERFLOW));
        System.out.println(r1.getContact(ContactType.HOMEPAGE));

        System.out.println(r1.getSection(SectionType.PERSONAL));
        System.out.println(r1.getSection(SectionType.OBJECTIVE));
        System.out.println(r1.getSection(SectionType.ACHIEVEMENT));
        System.out.println(r1.getSection(SectionType.QUALIFICATIONS));
        System.out.println(r1.getSection(SectionType.EXPERIENCE));
        System.out.println(r1.getSection(SectionType.EDUCATION));


    }
}