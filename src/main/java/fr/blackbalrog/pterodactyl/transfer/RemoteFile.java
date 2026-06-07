package fr.blackbalrog.pterodactyl.transfer;

public final class RemoteFile
{
	private final String  name;
	private final String  path;
	private final boolean directory;
	private final long    size;
	private final long    modifiedEpochMs;

	public RemoteFile(String name, String path, boolean directory, long size, long modifiedEpochMs)
	{
		this.name            = name;
		this.path            = path;
		this.directory       = directory;
		this.size            = size;
		this.modifiedEpochMs = modifiedEpochMs;
	}

	public String  getName()            { return name;            }
	public String  getPath()            { return path;            }
	public boolean isDirectory()        { return directory;       }
	public boolean isFile()             { return !directory;      }
	public long    getSize()            { return size;            }
	public long    getModifiedEpochMs() { return modifiedEpochMs; }
}
