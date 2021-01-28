const TaskManager = require('./TaskManager');

module.exports = class {
  async onCreate() {
    this.initializeState();
    this.taskManager = new TaskManager();

    const tasks = await this.taskManager.findAll();
    this.setTasks(tasks);
  }

  initializeState() {
    this.state = {
      tasks: [],
    };
  }

  setState(key, value) {
    this.state[key] = value;
  }

  setTasks(tasks) {
    this.setState('tasks', tasks);
  }
};
