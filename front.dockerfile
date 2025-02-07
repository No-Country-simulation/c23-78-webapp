FROM node:22-alpine
WORKDIR /usr/code
COPY ["./frontend/package.json", "/usr/code/"]
RUN npm install
EXPOSE 5173