module.exports = class {
  handleOpen(id) {
    this.emit('open', id)
  }

  handleClose() {
    this.emit('close');
  }

  handleUpdate(id) {
    const title = this.getEl("source").value;
    this.emit('update', title, id);
  }

  handleComplete(id) {
    this.emit('complete', id);
  }
}
