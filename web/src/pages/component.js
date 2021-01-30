const TaskManager = require('./TaskManager');

module.exports = class {
  async onCreate() {
    this.initializeState();
    this.taskManager = new TaskManager();

    await this.refreshTasks();
  }

  handleChangeTitle(event) {
    this.setTitle(event.target.value);
  }

  async handleClickAddTask() {
    await this.taskManager.insertOne(this.state.title);
    await this.refreshTasks();
    this.clearTitle();
  }

  async handleClickDeleteTask(id) {
    await this.taskManager.deleteOne(id);
    await this.refreshTasks();
  }

  initializeState() {
    this.state = {
      tasks: [],
      title: '',
    };
  }

  getTitleSelector() {
    return document.getElementById("task")
  }

  setState(key, value) {
    this.state[key] = value;
  }

  setTitle(title) {
    this.setState('title', title);
  }

  clearTitle() {
    this.setState('title', '');
  }

  setTasks(tasks) {
    this.setState('tasks', tasks);
  }

  async refreshTasks() {
    const tasks = await this.taskManager.findAll();
    this.setTasks(tasks);
  }
};
