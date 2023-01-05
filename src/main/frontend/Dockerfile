FROM node:14-alpine
WORKDIR .
COPY package*.json ./
RUN npm install
RUN npm install bootstrap
RUN npm install -S react-router-dombootstrap
RUN npm install axios
COPY . .
EXPOSE 3000
CMD ["npm", "start"]