public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        //2.4 создание задач
        manager.create(new Task("Задача 1", "описание Задачи 1"));
        manager.create(new Task("Задача 2", "описание Задачи 2"));

        manager.create(new Epic("Эпик 1", "описание Эпика 1"));
        manager.create(new SubTask("Подзадача 1", "описание Подзадачи 1", 3));
        manager.create(new SubTask("Подзадача 2", "описание Подзадачи 2", 3));

        manager.create(new Epic("Эпик 2", "описание Эпика 2"));
        manager.create(new SubTask("Подзадача 3", "описание Подзадачи 3", 6));

        manager.updateTask(new Task("Обновленный Task", "описание обновленного Task"), 1,
                "in_Progres");
        manager.updateTask(new SubTask("Обновленный subTask", "описание обновленного subTask", 6),
                7, "Done");

        //2.1 получение списка всех задач
        manager.printAllTask();
        //2.3 получение по идентификатору
        manager.printById(4);
        //3.1 получение списка всех подзадач определённого эпика
        manager.printingEpicSubtask(3);
        //2.6 удаление по идентификатору
        manager.clearById(3);
        //2.1 получение списка всех задач
        manager.printAllTask();
        //2.2 удаление всех задач
        manager.clearAllTask();
        //2.5 обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    }
}
