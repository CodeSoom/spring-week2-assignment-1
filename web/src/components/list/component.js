module.exports = class {
  handleOpen(id) {
    this.emit('open', id)
  }

  handleClose() {
    this.emit('close');
  }

  handleUpdate(title, id) {
    this.emit('update', title, id);
  }

  handleComplete(id) {
    this.emit('complete', id);
  }
}
