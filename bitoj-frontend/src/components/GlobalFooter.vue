<template>
  <footer class="global-footer">
    <div class="footer-content">
      <p>&copy; 2023 Liangshou. All rights reserved.</p>
      <a
        href="https://github.com/LiangshouX"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="https://github.com/LiangshouX.png"
          alt="GitHub Logo"
          width="20"
          height="20"
        />
      </a>
    </div>
  </footer>
</template>

<script>
export default {
  name: "GlobalFooter",
};
</script>

<style scoped>
.global-footer {
  background-color: aquamarine;
  color: #fff;
  padding: 16px;
  text-align: center;
  position: sticky;
  bottom: 0;
  //left: 0;
  //right: 0;
}

.footer-content {
  display: flex;
  align-items: center;
  justify-content: center;
}

.footer-content p {
  margin: 0;
}

.footer-content a {
  margin-left: 10px;
  text-decoration: none;
  color: #fff;
}

.footer-content img {
  vertical-align: middle;
}
</style>
