# GRADLE COMMANDS

* `gradle clean` - deletes the build directory of the project
<br>

---
<br>
<br>

# Plugin

## `apply plugin: java`

* treats the current project as java project
* generates jar file
* this works according to standard java packaging structure
<br>

---
<br>
<br>

# GRADLE TASKS
<br>

## writing a task

* **All of the below are equivalent**
    * project.task("task1")
    * task("task1")
    * task "task1"
    * task task1

* **Adding description**
    * task1.description = "task1 description comes here!"

* **Running a task**
    * the defination of a task i.e written inside `{}` actually is a lambda (or closure)
    * ```
        task task2 {
            description "This is test task"
            doLast {
                println "executing the task"
            }
        }

* **Task phases**
    * `initialisation`
    * `configuration` - executes code in task ie not in the action, builds dependency graph
    * `execution` - task actions are executed
    * ```
        task task3 {
            description "This is test task #3"
            doFirst {
                println "executing the do_first_task #1"
            }
            doFirst {
                println "executing the do_first_task #2"
            }
            doLast {
                println "executing the do_last_task #1"
            }
            doLast {
                println "executing the do_last_task #2"
            }
        }

    output of the above is, 
    ```
        executing the do_first_task #2
        executing the do_first_task #1
        executing the do_last_task #1
        executing the do_last_task #2
    ```

* **properties**
    * `def version = 2.0` - local property
    * use - `$version`

```
    def version = "3.0"

    task Task1 {
        description "sample task #1"
        doLast {
            version = "1.0"
            println "exec doLast - task 1"
            println "since verison - $version"
        }
    }

    task Task2 {
        description "sample task #2"
        doLast {
            version = "1.0"
            println "exec doLast - task 2"
            println "since verison - $version"
        }
    }

    task Task3 {
        description "sample task #3"
        dependsOn Task1
        doLast {
            version = "2.0"
            println "exec doLast - task 3"
            println "since verison - $version"
        }
    }

    task Task4 {
        description "sample task #4"
        dependsOn Task3, Task2
        doLast {
            println "exec doLast - task 4"
            println "since verison - $version"
        }
    }
```

<br>

## Task dependencies

* `mustRunAfter`
    * shuffles the order of execution only if both the tasks are falling in exection graph
    * incase of a circular dependency, gradle task run fails
* `shouldRunAfter`
    * ignores circular dependency
    * shuffles the order of execution only if both the tasks are falling in exection graph
* `dependesOn`
    * doFirst & doLast of dependent tasks are run first
    * this will run even if two tasks are not run together as they fall in the execution graph
* ```
    def version = "4.0"

    task Task1 {
        description "sample task #1"
        doLast {
            version = "1.0"
            println "exec doLast - task 1"
            println "since verison - $version"
        }
    }

    task Task2 {
        description "sample task #2"
        doLast {
            version = "1.0"
            println "exec doLast - task 2"
            println "since verison - $version"
        }
    }

    task Task3 {
        description "sample task #3"
        dependsOn Task1
        doLast {
            version = "2.0"
            println "exec doLast - task 3"
            println "since verison - $version"
        }
    }

    task Task4 {
        description "sample task #4"
        dependsOn Task3, Task2
        doLast {
            version = 3.0
            println "exec doLast - task 4"
            println "since verison - $version"
        }
    }

    task Task5 {
        description "sample task #5"
        shouldRunAfter Task1
        doLast {
            println "exec doLast - task 5"
            println "since verison - $version"
        }
    }