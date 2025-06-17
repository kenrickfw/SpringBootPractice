import { useEffect } from "react"
import { useSelector, useDispatch } from "react-redux"
import { setRecord } from "../redux/recordSlice"

import RecordDetails from "../components/RecordDetails"
import RecordForm from "../components/RecordForm"

const Home = () => {
  const records = useSelector((state) => state.record.records)
  const dispatch = useDispatch()

  useEffect(() => {
    const fetchWorkouts = async () => {
      const response = await fetch('http://localhost:8080/api/records')
      const json = await response.json()

      if (response.ok) {
        dispatch(setRecord(json))
      }
    }

    fetchWorkouts()
  }, [dispatch])

  return (
    <div className="home">
      <div className="records">
        {records && records.map(record => (
          <RecordDetails record={record} key={record.id} />
        ))}
      </div>
      <RecordForm />
    </div>
  )
}

export default Home