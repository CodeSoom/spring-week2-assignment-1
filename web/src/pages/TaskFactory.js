const axios = require('axios');

module.exports = class {
  async findAll() {
    const { data } = await axios.get('http://localhost:8080/tasks');
    return data;
  }

  async findOne(id) {
    const { data } = await axios.get(`http://localhost:8080/tasks/${id}`);
    return data;
  }

  async insertOne(title) {
    const { data } = await axios.post('http://localhost:8080/tasks', {
      title,
    });
    return data;
  }

  async modifyOne(id, title) {
    const { data } = await axios.put(`http://localhost:8080/tasks/${id}`, {
      title,
    });
    return data;
  }

  async deleteOne(id) {
    const { data } = await axios.delete(`http://localhost:8080/tasks/${id}`);
    return data;
  }
};
