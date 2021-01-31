const TaskFactory = require('./TaskFactory');

module.exports = class {
  async onCreate() {
    this.initializeState();
    this.taskFactory = new TaskFactory();

    await this.refreshTasks();
  }

  async refreshTasks() {
    const tasks = await this.taskFactory.findAll();
    this.setTasks(tasks);
  }

  handleChangeTitle(event) {
    this.setTitle(event.target.value);
  }

  handleChangeModifyTitle(event) {
    this.setModifyTitle(event.target.value);
  }

  async handleClickAddTask() {
    await this.taskFactory.insertOne(this.state.title);
    await this.refreshTasks();
    this.clearTitle();
  }

  async handleClickModifyTask(id, title) {
    this.setModifyID(id);
    this.setModifyTitle(title);
  }

  async handleClickDeleteTask(id) {
    await this.taskFactory.deleteOne(id);
    await this.refreshTasks();
  }

  handleClickCancelModifyTask() {
    this.clearModifyID();
  }

  async handleClickConfirmModifyTask() {
    await this.taskFactory.modifyOne(this.state.modifyID, this.state.modifyTitle);
    await this.refreshTasks();
    this.clearModifyID();
  }

  initializeState() {
    this.state = {
      tasks: [],
      title: '',
      modifyID: -1,
      modifyTitle: '',
    };
  }

  setState(key, value) {
    this.state[key] = value;
  }

  setTitle(title) {
    this.setState('title', title);
  }

  setModifyID(id) {
    this.setState('modifyID', id);
  }

  setModifyTitle(title) {
    this.setState('modifyTitle', title);
  }

  clearTitle() {
    this.setState('title', '');
  }

  clearModifyID() {
    this.setModifyID(-1);
  }

  setTasks(tasks) {
    this.setState('tasks', tasks);
  }
};
