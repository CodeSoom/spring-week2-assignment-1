<script>
  import axios from 'axios';

  let tasks = [];
  let title = '';
  let editId = null;

  async function loadTasks() {
    const { data } = await axios.get('http://localhost:8080/tasks');
    tasks = data;
  }

  async function addTask() {
    await axios.post('http://localhost:8080/tasks', {
      title,
    });
    await loadTasks();
    title = '';
  }

  function editTask(id) {
    editId = id;
    title = tasks.find(task => task.id === id)?.title;
  }

  function cancelEditTask() {
    editId = null;
    title = '';
  }

  async function updateTask() {
    await axios.patch(`http://localhost:8080/tasks/${editId}`, {
      title,
    });
    await loadTasks();
    editId = null;
    title = '';
  }

  async function deleteTask(id) {
    await axios.delete(`http://localhost:8080/tasks/${id}`);
    await loadTasks();
  }

  loadTasks();
</script>

<h1>ToDo</h1>
<ol>
  {#each tasks as task}
    <li>
      {#if task.id === editId}
        <input type="text" bind:value={title} />
        <span> </span>
        <button type="button" on:click={updateTask}>
          확인
        </button>
        <span> </span>
        <button type="button" on:click={cancelEditTask}>
          취소
        </button>
      {:else}
        {task.title}
        <span> </span>
        <button type="button" on:click={() => editTask(task.id)}>
          수정
        </button>
        <span> </span>
        <button type="button" on:click={() => deleteTask(task.id)}>
          완료
        </button>
      {/if}
    </li>
  {/each}
</ol>
{#if !editId}
  <p>
    <label for="input-title">
      할 일
    </label>
    <input id="input-title" type="text" bind:value={title} />
    <button type="button" on:click={addTask}>
      추가
    </button>
  </p>
{/if}
