FROM redis:7.4

EXPOSE 6379

VOLUME ["/data"]

CMD ["redis-server"]