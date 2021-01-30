const TaskManager = require('./TaskManager');

module.exports = class {
  async onCreate() {
    this.initializeState();
    this.taskManager = new TaskManager();

    await this.refreshTasks();
  }

  handleChangeTitle() {
    const titleSelector = this.getTitleSelector();
    const title = titleSelector.value;
    this.setTitle(title);
  }

  async handleClickAddTask() {
    const titleSelector = this.getTitleSelector();
    const title = titleSelector.value;

    await this.taskManager.insertOne(title);
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

  setTasks(tasks) {
    this.setState('tasks', tasks);
  }

  async refreshTasks() {
    const tasks = await this.taskManager.findAll();
    this.setTasks(tasks);
  }
};
