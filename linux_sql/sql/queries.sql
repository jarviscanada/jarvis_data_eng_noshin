/* Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group) */
SELECT 
  cpu_number, 
  id AS "host_id", 
  total_mem 
FROM 
  host_info 
GROUP BY 
  cpu_number, 
  host_id 
ORDER BY 
  cpu_number ASC, 
  total_mem DESC;

/* Find average of used memory over 5 minutes interval per host */
SELECT 
  host_id, 
  host_name, 
  timestamp, 
  AVG(used_mem_percentage) AS avg_used_mem_percentage 
FROM 
  (
    SELECT 
      u.host_id AS host_id, 
      i.hostname AS host_name, 
      (
        (
          (i.total_mem - u.memory_free * 1024)/ i.total_mem :: FLOAT
        )* 100
      ) AS used_mem_percentage, 
      (
        DATE_TRUNC('hour', u.timestamp) + INTERVAL '5 minute' * ROUND(
          DATE_PART('minute', u.timestamp) / 5.0
        )
      ) AS timestamp 
    FROM 
      host_info AS i 
      INNER JOIN host_usage AS u ON i.id = u.host_id
  ) AS x 
GROUP BY 
  timestamp, 
  host_id, 
  host_name 
ORDER BY 
  timestamp;

/* Detecting nodes that failed inserting more than 3 times */
With x AS(
  SELECT 
    *, 
    ROW_NUMBER() OVER (
      ORDER BY 
        timestamp, 
        host_id
    ) rn 
  FROM 
    host_usage
) 
SELECT 
  a.host_id as host_id, 
  a.timestamp as timestamp, 
  (
    SELECT 
      DATE_PART('hour', b.timestamp - a.timestamp) * 60 + DATE_PART(
        'minute', b.timestamp - a.timestamp
      )
  ) AS failed_times 
FROM 
  x a 
  JOIN x b ON a.rn + 1 = b.rn 
WHERE 
  (
    SELECT 
      DATE_PART('hour', b.timestamp - a.timestamp) * 60 + DATE_PART(
        'minute', b.timestamp - a.timestamp
      )
  ) >= 3 
ORDER BY 
  host_id

