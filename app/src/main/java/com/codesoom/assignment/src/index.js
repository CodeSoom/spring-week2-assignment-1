//    class MyClass {
//      onCreate() {
//        this.state = {
//          tasks: [],
//        }
//
//        this.loadTasks();
//      }
//
//      async loadTasks() {
//        const { data } = await axios.get('http://localhost:8080/tasks');
//        this.state.tasks = data;
//      }
//    }